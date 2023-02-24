<?php

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', [App\Http\Controllers\PostController::class, 'index']);

Auth::routes();

Route::get('/home', [App\Http\Controllers\PostController::class, 'index']);
Route::resource('users', App\Http\Controllers\UserController::class)->middleware('checkLogIn');
Route::resource('posts', App\Http\Controllers\PostController::class);
Route::resource('themes', App\Http\Controllers\ThemeController::class)->middleware('checkIfThemeMgr');
Route::post('theme/change', [App\Http\Controllers\ThemeController::class, 'changeTheme'])->name('themes.change');

Route::get('/posts/restore', [App\Http\Controllers\PostController::class, 'restore'])->name('posts.restore');
