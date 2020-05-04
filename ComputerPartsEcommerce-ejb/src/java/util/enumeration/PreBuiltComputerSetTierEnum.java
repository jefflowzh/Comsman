package util.enumeration;

public enum PreBuiltComputerSetTierEnum {
    PREMIUM {
        @Override
        public String toString() {
            return "Premium";
        }
    },
    REGULAR {
        @Override
        public String toString() {
            return "Regular";
        }
    },
    BUDGET {
        @Override
        public String toString() {
            return "Budget";
        }
    }
}
