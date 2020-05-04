import { Component, OnInit } from '@angular/core';
import { ComputerSet } from '../computer-set';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { RAM } from '../ram';
import { ComputerPart } from '../computer-part';
import { ComputerSetService } from '../computer-set.service';

@Component({
  selector: 'app-advanced-set-build-page',
  templateUrl: './advanced-set-build-page.component.html',
  styleUrls: ['./advanced-set-build-page.component.css']
})
export class AdvancedSetBuildPageComponent implements OnInit {

  
  currentComputerSet: ComputerSet;
  
  latestAddedComputerPart: ComputerPart;

  compatible: boolean = true;

  errorMessage: string;

  constructor(private router: Router, public sessionService: SessionService, public computerSetService: ComputerSetService) { }

  

  ngOnInit() {
    this.currentComputerSet = this.sessionService.getCurrentComputerSet();
    this.latestAddedComputerPart = this.sessionService.getLastAddedComputerPart();
    let customer = this.sessionService.getCurrentCustomer();
    
    if(this.latestAddedComputerPart != null){
      customer.currComputerBuild.push(this.sessionService.getLastAddedComputerPart());
      this.sessionService.setCurrentCustomer(customer);
    }
    
    this.compatibilityCheck();

    console.log(customer.currComputerBuild)
    console.log(this.currentComputerSet)
    console.log(this.latestAddedComputerPart)
  }

  resetCurrentComputerSet(){
    this.currentComputerSet.cpu = null;
    this.currentComputerSet.motherBoard = null;
    this.currentComputerSet.rams = [];
    this.currentComputerSet.psu = null;
    this.currentComputerSet.compCase = null;
    this.currentComputerSet.gpus = [];
    this.currentComputerSet.hdds = [];
    this.currentComputerSet.ssds = []
    this.currentComputerSet.waterCooler = null;
    this.currentComputerSet.airCooler = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let resetComputerPartList = [];
    let customer = this.sessionService.getCurrentCustomer();
    customer.currComputerBuild = resetComputerPartList;
    this.sessionService.setCurrentCustomer(customer);
    this.sessionService.setLastAddedComputerPart(null);
    this.compatible = true;
  }

  compatibilityCheck(){
    let computerPartIdList: number[] = [];
    for(let part of this.sessionService.getCurrentCustomer().currComputerBuild){
      computerPartIdList.push(part.productId);
    }
    if(this.latestAddedComputerPart === null){
      return;
    }

    let latestAddedComputerPartId = this.sessionService.getLastAddedComputerPart().productId;

    this.computerSetService.compatibilityCheck(computerPartIdList, latestAddedComputerPartId).subscribe(
      response => {
        this.compatible = response.compatible;
      }, error => {
        this.compatible = false;
        console.log('********** CompatibilityCheckComponent.ts componentConflict(): ' + error);
        this.errorMessage = "Error: " + error.slice(37);
      }
    );

  }

}
