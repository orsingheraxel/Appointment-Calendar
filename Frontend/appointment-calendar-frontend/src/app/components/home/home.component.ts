import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  images = [
    '/assets/carrete.jpeg',
    //'/assets/carrete2.jpeg',
    '/assets/carrete3.jpeg',
    // Agrega más rutas a imágenes si es necesario
  ];
  currentIndex = 0;
  currentImage = this.images[this.currentIndex];

  ngOnInit(): void {
    this.startCarousel();
  }

  startCarousel() {
    setInterval(() => {
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
      this.currentImage = this.images[this.currentIndex];
    }, 5000);
  }
}
