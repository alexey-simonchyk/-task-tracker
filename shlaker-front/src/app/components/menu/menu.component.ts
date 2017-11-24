import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project-service';
import * as $ from 'jquery';
import { environment } from '../../../environments/environment';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    @select('projects') projects: Observable<Project[]>;
    @select('selectedProject') selectedProject: Observable<Project>;

    imageEndPoint: string = `${environment.defaultImageEndPoint}`;

    menuState = {projects: false};

    constructor(private projectService: ProjectService) { }

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
