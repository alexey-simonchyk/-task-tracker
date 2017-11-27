import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { Project } from '../../../models/project.model';
import { environment } from '../../../../environments/environment';

@Component({
  templateUrl: './project-description.component.html',
  styleUrls: ['./project-description.component.css']
})
export class ProjectDescriptionComponent implements OnInit {

    imageEndPoint: string = `${environment.defaultImageEndPoint}`;
    @select('selectedProject') selectedProject: Observable<Project>;

    constructor() { }

    ngOnInit() {
    }

    addDeveloper() {

    }

}
