import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { Project } from '../../../models/project.model';

@Component({
  templateUrl: './project-description.component.html',
  styleUrls: ['./project-description.component.css']
})
export class ProjectDescriptionComponent implements OnInit {

    @select('selectedProject') selectedProject: Project;

    constructor() { }

    ngOnInit() {
    }

}
