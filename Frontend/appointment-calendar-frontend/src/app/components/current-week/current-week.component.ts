import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-current-week',
  standalone: true,
  imports : [CommonModule],
  templateUrl: './current-week.component.html',
  styleUrls: ['./current-week.component.css']
})
export class CurrentWeekComponent {
  showAvailableSlots: boolean = false;
  availableSlots: { day: string, date: Date, times: string[] }[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.generateAvailableSlots();
  }

  generateAvailableSlots() {
    const startTime = 9; // 09:00
    const endTime = 18; // 18:00

    const daysOfWeek = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];

    const today = new Date();
    for (let i = 0; i < 7; i++) {
      const date = new Date(today);
      date.setDate(today.getDate() + i);
      const dayString = daysOfWeek[date.getDay()];
      const dateString = date.toISOString().split('T')[0]; // Formato 'YYYY-MM-DD'

      const times = [];
      for (let hour = startTime; hour < endTime; hour++) {
        times.push(`${this.padZero(hour)}:00`);
        times.push(`${this.padZero(hour)}:30`);
      }
      times.push('18:00'); // Último turno disponible

      this.availableSlots.push({ day: dayString, date, times });
    }
  }

  padZero(num: number): string {
    return num < 10 ? `0${num}` : `${num}`;
  }

  selectSlot(date: Date, time: string) {
    const dateString = date.toISOString().split('T')[0];
    this.router.navigate(['/appointments'], { queryParams: { date: dateString, time } });
  }
}
