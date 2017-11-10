import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

    @select('selectedProject') selectedProject;

    constructor() { }

    ngOnInit() {
    }

}
