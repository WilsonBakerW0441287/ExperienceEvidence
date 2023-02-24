<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class CheckIfThemeAdmin
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure(\Illuminate\Http\Request): (\Illuminate\Http\Response|\Illuminate\Http\RedirectResponse)  $next
     * @return \Illuminate\Http\Response|\Illuminate\Http\RedirectResponse
     */
    public function handle(Request $request, Closure $next)
    {
        if (auth()->check()) { //is there a user logged in
            //check if the user has a role of theme manager
            if (auth()->user()->roles()->get()->pluck('name')->first() == 'Theme Manager') { //
                return $next($request); //if so, continue
            }
        }
        return redirect()->back()->with('status', 'You are not a Theme Manager');
    }
}
