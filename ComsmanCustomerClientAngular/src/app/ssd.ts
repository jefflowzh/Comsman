import { ComputerPart } from './computer-part'

export class SSD extends ComputerPart {

    manufacturer: string;
    type: string;
    capacity: number;
    formFactor: string;
    interface: string;
    NVME: boolean;

}
