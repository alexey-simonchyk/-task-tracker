import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project-service';

@Component({
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

    @select('selectedProject') selectedProject;

    constructor(
        private route: ActivatedRoute,
        private projectService: ProjectService
    ) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.projectService.getProject(params['projectId']);
        })
    }

}
