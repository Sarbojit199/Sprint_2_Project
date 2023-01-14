import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{
 

  employees!: Employee[];
  constructor(private employeeService: EmployeeService,private router: Router){}
  ngOnInit(): void{
    this.getEmployee(); 
  }
  private getEmployee(){
    this.employeeService.getEmployeesList().subscribe(data =>{
    this.employees=data;       
    });
      
    
  }
  employeedetails(id:number){
    this.router.navigate(['employee-details',id]); 
  }
  updateEmployee(id:number){
    this.router.navigate(['update-employee',id]);         //redirect ho jayega uss page par iss id ko lekar  router mein dekho
  }
  deleteEmployee(id:number){
    this.employeeService.deleteEmployee(id).subscribe(data=>{
      console.log(data);
      this.getEmployee();
    })
  }
 


}

