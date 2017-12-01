import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
    selector: 'app-modal-load-image',
    templateUrl: './modal-load-image.component.html',
    styleUrls: ['./modal-load-image.component.css'],
})
export class ModalLoadImageComponent implements OnInit {

    @Output('close') closeEvent: EventEmitter<boolean> = new EventEmitter();
    protected isImageLoaded: boolean = false;

    constructor() { }

    ngOnInit() {
    }

    protected close() {
        this.closeEvent.emit(false);
    }

    protected apply() {
        this.closeEvent.emit(true);
    }

    protected imageSelect(event, imageTarget) {
        if (event.target.files[0]) {
            this.isImageLoaded = true;
            console.log(event.target.files[0]);
            this.showImage(event.target, imageTarget)
        } else {
            this.isImageLoaded = false;
        }
    }

    private showImage(src, target) {
        let fr = new FileReader();
        fr.onload = function() { target.src = fr.result; };
        fr.readAsDataURL(src.files[0]);
    }
}
