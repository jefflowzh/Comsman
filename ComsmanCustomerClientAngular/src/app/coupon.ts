import { CouponTypeEnum } from "./coupon-type-enum.enum"

export class Coupon {

    couponId: number;
    code: string;
    startDate: Date;
    endDate: Date;
    loyaltyPointsRequired: number;
    couponType: CouponTypeEnum;
    percentageRate: number;
    flatAmount: number;
    isDisabled: false;

}
