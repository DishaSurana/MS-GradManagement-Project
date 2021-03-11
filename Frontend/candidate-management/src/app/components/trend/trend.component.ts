import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Chart } from 'chart.js';
import { arrayRotate, colors } from 'src/app/models/Color';
import { CandidateService } from 'src/app/services/candidate.service';

@Component({
  selector: 'app-trend',
  templateUrl: './trend.component.html',
  styleUrls: ['./trend.component.scss']
})
export class TrendComponent implements OnInit {

  chartType = 'pie';

  labels = [];
  gradCount = [];

  locationChart: any = null;
  skillChart: any = null;
  instituteChart: any = null;
  degreeChart: any = null;

  constructor(private snackBar: MatSnackBar, private candidateService: CandidateService) { }

  ngOnInit(): void {
    this.renderCharts();
  }

  renderCharts() {
    if (this.locationChart !== null) this.locationChart.destroy();
    if (this.skillChart !== null) this.skillChart.destroy();
    if (this.degreeChart !== null) this.degreeChart.destroy();
    if (this.instituteChart !== null) this.instituteChart.destroy();

    this.getTrendData("location");
    this.getTrendData("skill");
    this.getTrendData("degree");
    this.getTrendData("institute");
  }

  createChart(attr) {
    return new Chart(attr + 'Canvas', {
      type: this.chartType,
      data: {
        labels: this.labels,
        datasets: [
          {
            label: '# of Grads ' + attr + ' wise',
            data: this.gradCount,
            // borderColor: '#3cba9f',
            backgroundColor: arrayRotate(colors, Math.random() * Math.floor(colors.length) | 0), //rotate
            fill: true,
            maxBarThickness: 70,
          }
        ]
      },
      options: {
        legend: {
          display: true
        },
        scales: {
          xAxes: [{
            display: this.chartType === "pie" ? false : true
          }],
          yAxes: [{
            display: true,
            ticks: {
              beginAtZero: true,
              stepSize: 1
            }
          }],
        }
      }
    });
  }

  getTrendData(attr: String) {
    this.candidateService.getCandidateTrend(attr).subscribe((data) => {
      switch (attr) {
        case "location":
          this.locationChart = this.extractLocationTrendDetails(data);
          break;
        case "skill":
          this.skillChart = this.extractSkillTrendDetails(data);
          break;
        case "degree":
          this.degreeChart = this.extractDegreeTrendDetails(data);
          break;
        case "institute":
          this.instituteChart = this.extractInstituteTrendDetails(data);
      }
    }, error => {
      this.openSnackBar(attr + " Trend data can't be loaded!");
    });
  }

  extractLocationTrendDetails(data) {
    this.labels = data.map(element => element[0]);
    this.gradCount = data.map(element => element[1]);
    return this.createChart("location");
  }

  extractSkillTrendDetails(data) {
    this.labels = data.map(element => element[0].skillName);
    this.gradCount = data.map(element => element[1]);
    return this.createChart("skill");
  }

  extractInstituteTrendDetails(data) {
    this.labels = data.map(element => element[0].name);
    this.gradCount = data.map(element => element[1]);
    return this.createChart("institute");
  }

  extractDegreeTrendDetails(data) {
    this.labels = data.map(element => element[0]);
    this.gradCount = data.map(element => element[1]);
    return this.createChart("degree");
  }

  openSnackBar(msg) {
    this.snackBar.open(msg, '',
      {
        duration: 3000,
        horizontalPosition: 'right',
        verticalPosition: 'bottom',
        panelClass: ['snack-bar-color']
      }
    );
  }
}
