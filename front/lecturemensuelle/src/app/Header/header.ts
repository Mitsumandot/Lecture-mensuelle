import { Component, EventEmitter, Output, OnInit, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Subject, debounceTime, distinctUntilChanged, takeUntil } from 'rxjs';
import { BookService } from '../core/services/book.service';

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './header.html',
    styleUrl: './header.css'
})
export class Header implements OnInit, OnDestroy {
    searchQuery: string = '';
    private searchSubject = new Subject<string>();
    private destroy$ = new Subject<void>();


    @Output() searchEvent = new EventEmitter<string>();

    ngOnInit(): void {
        this.searchSubject.pipe(
            debounceTime(300),
            distinctUntilChanged(),
            takeUntil(this.destroy$)
        ).subscribe(query => {
            this.searchEvent.emit(query);
        });
    }

    ngOnDestroy(): void {
        this.destroy$.next();
        this.destroy$.complete();
    }

    onSearch(): void {
        this.searchSubject.next(this.searchQuery);

    }


}
