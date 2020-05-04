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
    cpu: CPU;
    motherBoard: MotherBoard;
    rams: RAM[];
    psu: PowerSupply;
    compCase: ComputerCase;
    gpus: GPU[];
    hdds: HDD[];
    ssds: SSD[];
    waterCooler: CPUWaterCooler;
    airCooler: CPUAirCooler;
    warrantyLengthInYears: number;
    assemblyAssignedTo: Staff;
    assembyComplete: boolean;
    lineItem: LineItem;
    price: number;
    
    
    
   
    
   
    
    
    
    
    
    
    
}
