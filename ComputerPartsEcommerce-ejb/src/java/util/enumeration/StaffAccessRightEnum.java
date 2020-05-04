package util.enumeration;

public enum StaffAccessRightEnum {
    MANAGER {        
        public String toString() {
            return "Manager";
        }
    },
    TECHNICIAN {
        public String toString() {
            return "Technician";
        }
    }
}
