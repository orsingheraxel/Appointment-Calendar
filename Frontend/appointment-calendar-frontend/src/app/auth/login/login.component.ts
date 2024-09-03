import { CommonModule, NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule ,NgClass],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  signInForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router){
    this.signInForm=formBuilder.group({
        email:['',[Validators.required,Validators.email]],
        password:['',[Validators.required,Validators.minLength(6)]]
    });
  }

  enviar(event: Event) {
    event.preventDefault(); // Evita que la página se recargue al enviar el formulario
  }

  hasErrors(field: string, typeError: string) {
    return this.signInForm.get(field)?.hasError(typeError) && this.signInForm.get(field)?.touched;
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

}
