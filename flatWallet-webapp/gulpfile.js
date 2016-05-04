var gulp = require('gulp');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var connect = require('gulp-connect');
var runSequence = require('run-sequence');

gulp.task('sass', function () {
    return gulp.src([
            'assets/sass/ie8.scss',
            'assets/sass/ie9.scss',
            'assets/sass/main.scss'
        ])
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('assets/css'));
});

gulp.task('concat', function () {
    return gulp.src([
            'app/app.js',
            'app/**/*.js'
        ])
        .pipe(concat('app.concat.js'))
        .pipe(gulp.dest('./'));
});

gulp.task('watch:js', function () {
    return gulp.watch('app/**/*.js', ['concat']);
});

gulp.task('watch:sass', function () {
    return gulp.watch('assets/sass/**/*.scss', ['sass']);
});


gulp.task('connect', function () {
    connect.server({
        port: 3001
    });
});


gulp.task('dev', function () {
    runSequence(
        ['sass', 'concat'],
        ['watch:js', 'watch:sass', 'connect']
    )
});
