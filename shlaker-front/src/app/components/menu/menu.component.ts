import { Component, OnInit } from '@angular/core';
import { NgRedux, select } from 'ng2-redux';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project-service';
import { IAppState } from '../../app.store';
import { DESELECT_PROJECT } from "../../actions";
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    @select('projects') projects: Project[];

    constructor(private projectService: ProjectService,
                private ngRedux: NgRedux<IAppState>,
                private router: Router) { }

    ngOnInit() {
        this.projectService.getProjects();
    }

    selectProject(projectId) {
        if (projectId) {
            this.router.navigate(['/project', { projectId: projectId }])
        } else {
            // this.ngRedux.dispatch({  type: DESELECT_PROJECT})
        }
    }

}
