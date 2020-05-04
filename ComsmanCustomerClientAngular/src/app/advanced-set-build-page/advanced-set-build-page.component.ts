import { Component, OnInit } from '@angular/core';
import { ComputerSet } from '../computer-set';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { RAM } from '../ram';
import { ComputerPart } from '../computer-part';
import { ComputerSetService } from '../computer-set.service';
import { CPU } from '../cpu';
import { CPUWaterCooler } from '../cpuwater-cooler';
import { CPUAirCooler } from '../cpuair-cooler';
import { GPU } from '../gpu';
import { SSD } from '../ssd';
import { HDD } from '../hdd';
import { MotherBoard } from '../motherboard';
import { PowerSupply } from '../powersupply';
import { ComputerCase } from '../computer-case';

@Component({
  selector: 'app-advanced-set-build-page',
  templateUrl: './advanced-set-build-page.component.html',
  styleUrls: ['./advanced-set-build-page.component.css']
})
export class AdvancedSetBuildPageComponent implements OnInit {


  currentComputerSet: ComputerSet;

  latestAddedComputerPart: ComputerPart;

  compatible: boolean = true;

  errorMessage: string;

  totalPrice: number = 0;

  constructor(private router: Router, public sessionService: SessionService, public computerSetService: ComputerSetService) { }

  ngOnInit() {
    this.currentComputerSet = this.sessionService.getCurrentComputerSet();
    this.latestAddedComputerPart = this.sessionService.getLastAddedComputerPart();
    let customer = this.sessionService.getCurrentCustomer();
    for (let part of customer.currComputerBuild) {
      this.totalPrice += part.price;
    }

    // if(this.latestAddedComputerPart != null){
    //   customer.currComputerBuild.push(this.sessionService.getLastAddedComputerPart());
    //   this.sessionService.setCurrentCustomer(customer);
    // }

    this.compatibilityCheck();

    console.log(customer.currComputerBuild)
    console.log(this.currentComputerSet)
    console.log(this.latestAddedComputerPart)
  }

  resetCurrentComputerSet() {
    this.currentComputerSet.cpu = null;
    this.currentComputerSet.motherBoard = null;
    this.currentComputerSet.rams = [];
    this.currentComputerSet.psu = null;
    this.currentComputerSet.compCase = null;
    this.currentComputerSet.gpus = [];
    this.currentComputerSet.hdds = [];
    this.currentComputerSet.ssds = []
    this.currentComputerSet.waterCooler = null;
    this.currentComputerSet.airCooler = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let resetComputerPartList = [];
    let customer = this.sessionService.getCurrentCustomer();
    customer.currComputerBuild = resetComputerPartList;
    this.sessionService.setCurrentCustomer(customer);
    this.sessionService.setLastAddedComputerPart(null);
    this.compatible = true;
    this.totalPrice = 0;
  }

  compatibilityCheck() {
    let computerPartIdList: number[] = [];
    for (let part of this.sessionService.getCurrentCustomer().currComputerBuild) {
      computerPartIdList.push(part.productId);
    }
    if (this.latestAddedComputerPart === null) {
      return;
    }

    let latestAddedComputerPartId = this.sessionService.getLastAddedComputerPart().productId;

    this.computerSetService.compatibilityCheck(computerPartIdList, latestAddedComputerPartId).subscribe(
      response => {
        if (response.compatible == true) {
          this.compatible = response.compatible;
        }
        else {
          this.compatible = false;
          this.errorMessage = response.message;
        }

      }, error => {
        this.compatible = false;
        console.log('********** CompatibilityCheckComponent.ts componentConflict(): ' + error);
        this.errorMessage = "Error: " + error.slice(37);
      }
    );

  }

  getPrice() {
    this.totalPrice = 0;
    let customer = this.sessionService.getCurrentCustomer();
    for (let part of customer.currComputerBuild) {
      this.totalPrice += part.price;
    }
  }

  clearCpu() {
    let name = this.currentComputerSet.cpu.name;
    this.currentComputerSet.cpu = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {

      if (computerPart[i].name == name) {
        computerPart.splice(i, 1);
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearCpuCooler() {
    let name: string;
    if (this.currentComputerSet.waterCooler != null) {
      name = this.currentComputerSet.waterCooler.name;
      this.currentComputerSet.waterCooler = null;
    }
    if (this.currentComputerSet.airCooler != null) {
      name = this.currentComputerSet.airCooler.name;
      this.currentComputerSet.airCooler = null;
    }
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      if (computerPart[i].name == name) {
        computerPart.splice(i, 1);
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }
  //!mb.suportedMemorySpeed.some(r => this.selectedSupportedMemorySpeed.includes(r)
  clearGpu() {
    let names: string[] = []
    for (let gpu of this.currentComputerSet.gpus) {
      names.push(gpu.name);
    }
    this.currentComputerSet.gpus = [];
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      for (let j = 0; j < names.length; j++) {
        if (computerPart[i].name == names[j]) {
          computerPart.splice(i, 1);
        }
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearSsd() {
    let names: string[] = []
    for (let gpu of this.currentComputerSet.ssds) {
      names.push(gpu.name);
    }
    this.currentComputerSet.ssds = []
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      for (let j = 0; j < names.length; j++) {
        if (computerPart[i].name == names[j]) {
          computerPart.splice(i, 1);
        }
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearHdd() {
    let names: string[] = []
    for (let gpu of this.currentComputerSet.hdds) {
      names.push(gpu.name);
    }
    this.currentComputerSet.hdds = [];
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      for (let j = 0; j < names.length; j++) {
        if (computerPart[i].name == names[j]) {
          computerPart.splice(i, 1);
        }
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearMotherboard() {
    let name = this.currentComputerSet.motherBoard.name;
    this.currentComputerSet.motherBoard = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      if (computerPart[i], name == name) {
        computerPart.splice(i, 1);
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearPsu() {
    let name = this.currentComputerSet.psu.name;
    this.currentComputerSet.psu = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      if (computerPart[i].name == name) {
        computerPart.splice(i, 1);
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearRam() {
    let names: string[] = []
    for (let gpu of this.currentComputerSet.rams) {
      names.push(gpu.name);
    }
    this.currentComputerSet.rams = [];
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      for (let j = 0; j < names.length; j++) {
        if (computerPart[i].name == names[j]) {
          computerPart.splice(i, 1);
        }
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }

  clearCompCase() {
    let name = this.currentComputerSet.compCase.name;
    this.currentComputerSet.compCase = null;
    this.sessionService.setCurrentComputerSet(this.currentComputerSet);
    let customer = this.sessionService.getCurrentCustomer();
    let computerPart = customer.currComputerBuild;
    for (let i = 0; i < computerPart.length; i++) {
      if (computerPart[i].name == name) {
        computerPart.splice(i, 1);
      }
    }
    customer.currComputerBuild = computerPart;
    this.sessionService.setCurrentCustomer(customer);
    this.getPrice();
    this.sessionService.setLastAddedComputerPart(null);
  }
}
