package util.enumeration;

public enum OrderStatusEnum {
    UNASSIGNED {
        @Override
        public String toString() {
            return "Unassigned";
        }
    },
    PARTIALLY_ASSIGNED {
        @Override
        public String toString() {
            return "Partially Assigned";
        }
    },
    VOIDED {
        @Override
        public String toString() {
            return "Voided";
        }
    },
    ASSIGNED {
        @Override
        public String toString() {
            return "Assigned";
        }
    },
    COMPLETED {
        @Override
        public String toString() {
            return "Completed";
        }
    },
    FULFILLED {
        @Override
        public String toString() {
            return "Fulfilled";
        }
    }
}
