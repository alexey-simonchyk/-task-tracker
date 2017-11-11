import { Component, OnInit } from '@angular/core';
import { NgRedux, select } from 'ng2-redux';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project-service';
import { IAppState } from '../../app.store';
import { DESELECT_PROJECT } from "../../actions";
import { Router } from '@angular/router';
import * as $ from 'jquery';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    @select('projects') projects: Project[];
    @select('selectedProject') selectedProject: Project;

    state = {projects: false, tasks: false};

    constructor(private projectService: ProjectService,
                private ngRedux: NgRedux<IAppState>,
                private router: Router) { }

    ngOnInit() {
        this.projectService.getProjects();
    }

    selectProject(projectId) {
        if (projectId) {
            this.router.navigate(['/project', projectId]);
        } else {
            this.ngRedux.dispatch({  type: DESELECT_PROJECT })
            this.router.navigate(['/']);
        }
    }

    changeState(target) {
        let element;
        switch (target) {
            case 'projects':
                element = $('#projects_close');
                break;
            case 'tasks':
                element = $('#tasks_close');
                break;
        }

        if (this.state[target]) {
            element.slideDown('slow');
        } else {
            element.slideUp('slow');
        }

        this.state[target] = !this.state[target];
    }

}
