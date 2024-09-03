import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  styleUrls: ['./appointment.component.css'],
})
export class AppointmentComponent implements OnInit {
  
  appointment = {
    serviceType: '',
    date: '',
    time: '',
    phoneNumber: '',
  };

  minDate?: string;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.minDate = new Date().toISOString().split('T')[0];

    // Captura de parámetros de la URL
    this.route.queryParams.subscribe(params => {
      this.appointment.date = params['date'] || this.minDate;
      this.appointment.time = params['time'] || '';
    });
  }

  submitAppointment() {
    if (!this.isFormValid()) {
      return;
    }

    console.log('Cita agendada:', this.appointment);
    alert('¡Cita agendada exitosamente!');
    this.resetForm();
  }

  isFormValid(): boolean {
    const today = new Date().toISOString().split('T')[0];

    if (!this.appointment.serviceType) {
      alert('Por favor, selecciona un tipo de servicio.');
      return false;
    }

    if (!this.appointment.date) {
      alert('Por favor, selecciona una fecha.');
      return false;
    }

    if (this.appointment.date < today) {
      alert('La fecha seleccionada no puede ser en el pasado.');
      return false;
    }

    if (!this.appointment.time) {
      alert('Por favor, selecciona una hora.');
      return false;
    }

    if (!this.isTimeValid(this.appointment.time)) {
      alert('Por favor, selecciona una hora con minutos en incrementos de 30 (ej. 09:00, 09:30).');
      return false;
    }

    if (!this.appointment.phoneNumber) {
      alert('Por favor, ingresa un número de teléfono.');
      return false;
    }

    if (!this.isPhoneNumberValid(this.appointment.phoneNumber)) {
      alert('Por favor, ingresa un número de teléfono válido de 10 dígitos.');
      return false;
    }

    return true;
  }

  isTimeValid(time: string): boolean {
    const [hours, minutes] = time.split(':').map(Number);
    return minutes === 0 || minutes === 30;
  }

  isPhoneNumberValid(phoneNumber: string): boolean {
    const phonePattern = /^[0-9]{10}$/;
    return phonePattern.test(phoneNumber);
  }

  resetForm() {
    this.appointment = {
      serviceType: '',
      date: '',
      time: '',
      phoneNumber: '',
    };
  }
}
