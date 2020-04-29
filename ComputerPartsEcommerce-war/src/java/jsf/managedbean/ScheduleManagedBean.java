package jsf.managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import entity.ComputerSet;
import entity.CustomerOrder;
import entity.Staff;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author weidonglim
 */
@Named(value = "scheduleManagedBean")
@ViewScoped
public class ScheduleManagedBean implements Serializable {

    @EJB(name = "CustomerOrderSessionBeanLocal")
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @EJB(name = "ComputerSetSessionBeanLocal")
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;

    private List<ComputerSet> sets;
    private List<CustomerOrder> deliveries;

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void postConstruct() {
        Staff staff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaffEntity");
        setSets(computerSetSessionBeanLocal.retrieveComputerSetsByStaffAssignedTo(staff.getUserId(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE));
        setDeliveries(customerOrderSessionBeanLocal.retrieveCustomerOrdersByDeliveryStaff(staff.getUserId(), Boolean.TRUE));

        eventModel = new DefaultScheduleModel();

        if (sets != null) {
            for (ComputerSet s : sets) {
                CustomerOrder order = s.getLineItem().getCustomerOrder();

                eventModel.addEvent(new DefaultScheduleEvent("Computer Building - Order Id: " + order.getCustomerOrderId() + ", LineItem Id: " + s.getLineItem().getLineItemId(), order.getOrderDate(), order.getCompleteBy(), "computerbuilding"));
            }
        } else {
            System.out.println("null");
        }

        if (deliveries != null) {
            for (CustomerOrder c : deliveries) {
                eventModel.addEvent(new DefaultScheduleEvent("Delivery - Order Id: " + c.getCustomerOrderId() , c.getCompleteBy() , c.getDeliverBy(), "delivery"));
            }
        }

    }

    public ScheduleManagedBean() {

    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    // dont need this
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<ComputerSet> getSets() {
        return sets;
    }

    public void setSets(List<ComputerSet> sets) {
        this.sets = sets;
    }

    public List<CustomerOrder> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<CustomerOrder> deliveries) {
        this.deliveries = deliveries;
    }
}
