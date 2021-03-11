import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  images = [
    "assets/image1.jpg",
    "../assets/image2.jpg",
    "../assets/image3.jpg",
    "../assets/image4.jpg",
    "../assets/image5.jpeg",
  ]

  constructor() { }

  ngOnInit(): void {
  }
}
