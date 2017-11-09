import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { User } from '../../models/user.model';
import { Task } from "../../models/task.model";

@Component({
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {

    @select() developers: User[];
    @select() tasks: Task[];

    constructor() { }

    ngOnInit() {
    }

}
