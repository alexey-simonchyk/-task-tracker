import { Task } from './models/task.model';
import { Project } from './models/project.model';
import { DESELECT_PROJECT, LOAD_PROJECTS, LOAD_TASKS, SELECT_PROJECT } from './actions';
import { IAppState } from './app.store';

export interface IAppState {
    projects: Project[];
    tasks: Task[];
    selectedProject: Project;
    selectedTask: Task;
}

export const INITIAL_STATE: IAppState = {
    projects: [],
    tasks: [],
    selectedTask: new Task(),
    selectedProject: new Project()
};

export function projectReducer(state = {projects: []}, action) {
    switch(action.type) {
        case LOAD_PROJECTS: return Object.assign(state, {projects: action.projects})
    }
    return state;
}

export function taskReducer(state = {tasks: []}, action) {
    return state;
}

export function selectedTaskReducer(state = {selectedTask: null}, action) {
    return state;
}

export function selectedProjectReducer(state = {selectedProject: null}, action) {
    switch(action.type) {
        case SELECT_PROJECT: return Object.assign(state, {selectedProject: action.project})
        case DESELECT_PROJECT: return Object.assign(state, {selectedProject: null})
    }
    return state;
}
