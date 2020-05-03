import { PreBuiltComputerSetTierEnum } from './pre-built-computer-set-tier-enum.enum';
import { CPU } from './cpu';
import { MotherBoard } from './motherBoard';
import { RAM } from './ram';
import { PowerSupply } from './powersupply';
import { ComputerCase } from './computer-case';
import { GPU } from './gpu';
import { HDD } from './hdd';
import { SSD } from './ssd';
import { CPUWaterCooler } from './cpuwater-cooler';
import { CPUAirCooler } from './cpuair-cooler';

export class PreBuiltComputerSetModel {

    preBuiltComputerSetModelId: number;
    warrentyLengthInYears: number;
    price: number;
    preBuiltComputerSetTier: PreBuiltComputerSetTierEnum;
    isEnabled: boolean;
    cpu: CPU;
    motherboard: MotherBoard;
    rams: RAM[];
    psu: PowerSupply;
    compCase: ComputerCase;
    gpus: GPU[];
    hdds: HDD[];
    ssds: SSD[];
    waterCooler: CPUWaterCooler;
    airCooler: CPUAirCooler;

}
