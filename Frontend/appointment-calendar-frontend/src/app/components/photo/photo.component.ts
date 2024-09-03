import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  standalone : true,
  imports : [CommonModule],
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  serviceType: string | null = null;
  images: string[] = [];

  corteImages = [
    '/assets/corte.jpeg',
    '/assets/corte.jpeg',
    '/assets/corte.jpeg'
  ];

  globalImages = [
    '/assets/global.jpeg',
    '/assets/global.jpeg',
    '/assets/global.jpeg'
  ];

  mechasImages = [
    '/assets/mechitas.jpeg',
    '/assets/mechitas.jpeg',
    '/assets/mechitas.jpeg'
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.serviceType = params.get('typeService');
      this.setImages();
    });
  }

  setImages(): void {
    if (this.serviceType === 'photo-corte') {
      this.images = this.corteImages;
    } else if (this.serviceType === 'photo-global') {
      console.log("parametro ", this.serviceType);
      this.images = this.globalImages;
    } else if (this.serviceType === 'photo-mechas') {
      this.images = this.mechasImages;
    }
  }
}
