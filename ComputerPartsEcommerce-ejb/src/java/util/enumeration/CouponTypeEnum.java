package util.enumeration;

public enum CouponTypeEnum {
    PERCENTAGE {
        public String toString() {
            return "Percentage";
        }
    },
    FLAT_AMOUNT {
        public String toString() {
            return "Flat Amount";
        }
    },
    FREE_DELIVERY {
        public String toString() {
            return "Free Delivery";
        }
    }
}
