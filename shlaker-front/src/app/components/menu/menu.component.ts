import { Component, OnInit } from '@angular/core';
import { NgRedux, select } from 'ng2-redux';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project-service';
import { IAppState } from '../../app.store';
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

    menuState = {projects: false};

    constructor(private projectService: ProjectService,
                private ngRedux: NgRedux<IAppState>,
                private router: Router) { }

    ngOnInit() {
        this.projectService.getProjects();
    }

    changeMenuState(target) {
        let element;
        switch (target) {
            case 'projects':
                element = $('#projects_close');
                break;
        }

        if (this.menuState[target]) {
            element.slideDown('slow');
        } else {
            element.slideUp('slow');
        }

        this.menuState[target] = !this.menuState[target];
    }

}
