<?php

namespace App\Providers;

use App\Models\Theme;
use http\Cookie;
use Illuminate\Support\Facades\View;
use Illuminate\Support\ServiceProvider;


class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }

    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot(): void
    {
        //add a lsit of themes to make them available to all views ESPCIALLY the app.blade.php
        View::share('themes', Theme::all());
//        $themesList = Theme::all();
//        View::share('themes', $themesList);//this is effectively a global variable
    }
}
