<?php

namespace App\Http\Middleware;

use App\Models\User;
use Closure;
use Illuminate\Http\Request;

class CheckIfLoggedin
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
        //this is a middleware that will check if the user is logged in
        //if they are, then the user will be allowed to access the user mgmt page
        //if they are not, then the user will be redirected to the page they tried to acces it from
//        $authorizedUser = User::whereHas('roles', function ($query) {
//            $query->where('role_id', 1 || 2);
//        })->get();
        //this function is all find and dandy if you want to check for if there are ANY users with the role
        //but what if you want to check if the user is logged in and has a specific role

        //if the user is logged in and has a role of 1 or 2, then they are allowed to access the page
        //check if there is a user logged in
        if (auth()->check()) {
            //check if the user has a role of 1 or 2
            if (auth()->user()->roles()->get()->pluck('name')->first() == 'User Admin') {
                return $next($request);
            }
        }
        return redirect()->back()->with('status', 'You are not authorized to access this page');

    }
}
