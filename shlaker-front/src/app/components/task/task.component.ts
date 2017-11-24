import { environment } from './../../../environments/environment';
import { NgRedux, select } from 'ng2-redux';
import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task-service';
import { IAppState } from "../../app.store";
import { ActivatedRoute } from '@angular/router';
import { Task } from '../../models/task.model';
import { Project } from '../../models/project.model';
import { Observable } from 'rxjs/Observable';
import * as $ from 'jquery';

@Component({
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

    @select('selectedProject') selectedProject: Observable<Project>;
    @select('selectedTask') selectedTask: Observable<Task>;
    
    imageEndPoint: string = `${environment.defaultImageEndPoint}`;
    daysLeft: number = 0;
    isOver: boolean = false;
    
    private oneDay = 24 * 60 * 60 * 1000; 

    constructor(
        private ngRedux: NgRedux<IAppState>,
        private taskService: TaskService,
        private route: ActivatedRoute
    ) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let taskId = params['taskId'];
            this.taskService.getTask(taskId).then(data => {
                this.selectedTask.subscribe(task => {
                    this.getDaysBetween(new Date(task.startTime), new Date(task.endTime));
                })
            });
        })
    }

    private getDaysBetween(startDate: Date, endDate: Date) {
        let currentDate = new Date(Date.now());

        let taskTime = Math.round(Math.abs((endDate.getTime() - startDate.getTime()) / this.oneDay));
        let leftTime = Math.round(Math.abs((currentDate.getTime() - startDate.getTime()) / this.oneDay));

        if (leftTime >= taskTime) {
            this.isOver = true;
            this.daysLeft = 0;
            $('.task_time_passed').animate({
                width: '100%'
            }, 500);
        } else  {
            let temp = Math.round(leftTime * 100 / taskTime);
            this.daysLeft = taskTime - leftTime;
            $('.task_time_passed').animate({
                width: temp.toString() + '%'
            }, 500);
        }
    }

}
