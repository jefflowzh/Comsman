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

export class ComputerSet {

    computerSetId: number;
    warrantyLengthInYears: number;
    assembyCompleted: boolean;
    price: number;
    cpu: CPU;
    cpuAirCooler: CPUAirCooler;
    cpuWaterCooler: CPUWaterCooler;
    computerCase: ComputerCase;
    gpu: GPU;
    hdd: HDD;
    motherBoard: MotherBoard;
    powerSupply: PowerSupply;
    ram: RAM;
    ssd: SSD;

}
