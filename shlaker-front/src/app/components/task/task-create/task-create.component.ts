import { Component, OnInit } from '@angular/core';
import { Task } from '../../../models/task.model';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../../../services/task-service';

@Component({
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent implements OnInit {

    newTask: Task = new Task();
    private projectId: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private taskService: TaskService
    ) { }

    ngOnInit() {
        let today = new Date(Date.now());
        today.setHours(0, 0, 0, 0);
        this.newTask.startTime = today;
        this.route.params.subscribe(params => {
            this.projectId = params['projectId'];
        })
    }

    onSubmit() {
        this
            .taskService
            .createTask(this.projectId, this.newTask)
            .then(data => {
                this.router.navigate(['/project', this.projectId])
            });
    }

}
