import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent  implements OnInit{
  id!: number;
  ngOnInit(): void {                  //update marne se wo details khulega jo waha pe likha rehega
    this.id=this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee=data;
    },error=> console.log(error));
  }
  employee: Employee =new Employee();
constructor(private employeeService: EmployeeService,private route:ActivatedRoute,private router:Router){}
  
onSubmit(){
this.employeeService.updateEmployees(this.id,this.employee).
subscribe(data=>{
  this.employee=new Employee();
 this.goToEmployeeList();
  }
,error=>console.log(error));
}
  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }
  
}
