import { CPU } from './cpu';
import { CPUAirCooler } from './cpuair-cooler';
import { CPUWaterCooler } from './cpuwater-cooler';
import { ComputerCase } from './computer-case';
import { GPU } from './gpu';
import { HDD } from './hdd';
import { MotherBoard } from './motherboard';
import { PowerSupply } from './powersupply';
import { RAM } from './ram';
import { SSD } from './ssd';
import { LineItem } from './line-item';
import { Staff } from './staff';

export class ComputerSet {

    computerSetId: number;
    warrantyLengthInYears: number;
    assembyComplete: boolean;
    price: number;
    cpu: CPU;
    CPUAirCooler: CPUAirCooler;
    CPUWaterCooler: CPUWaterCooler;
    compCase: ComputerCase;
    gpus: GPU[];
    hdds: HDD[];
    motherBoard: MotherBoard;
    psu: PowerSupply;
    rams: RAM[];
    ssds: SSD[];
    lineItem: LineItem;
    assemblyAssignedTo: Staff;

    // only in typscript class
    tier: string;

    constructor() {
        this.gpus = [];
        this.hdds = [];
        this.rams = [];
        this.ssds = [];
    }
}
