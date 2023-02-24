<?php

namespace App\Http\Controllers;

use App\Models\Post;
use App\Models\Role;
use App\Models\User;
use Illuminate\Contracts\Foundation\Application;
use Illuminate\Contracts\View\Factory;
use Illuminate\Contracts\View\View;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Routing\Redirector;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UserController
{
    public function index()
    {
//        $users = User::with('roles')->get();
        $users = User::withWhereHas('roles')->get(); //this shows only users with elevated privileges

        return view('users.index', compact('users'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return Application|Factory|View
     */
    public function create()
    {
        //go to a view to create a new user
        $roles = Role::all();
        return view('users.create', compact('roles'));

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param Request $request
     * @return Application|RedirectResponse|Redirector
     */
    public function store(Request $request)
    {
        //we need to validate information then save it to the database
        $request->validate([
            'name' => 'required',
            'email' => 'required | unique:users',
            'password' => 'required | min:3',
        ]);
        //save the data to the database
        $user = User::create([
            'name' => $request['name'],
            'email' => $request['email'],
            'password' => Hash::make($request['password']),
        ])->roles()->sync($request->role);

        //redirect the user to the users page
        return redirect(route('users.index'))->with('status', 'User Created Successfully');

    }

    /**
     * Display the specified resource.
     *
     * @param User $user
     * @return Application|Factory|View
     */
    public function show(User $user)
    {
        //show the individual user and their roles
        $user = User::with('roles')->find($user->id);
        return view('users.show', compact('user'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param User $user
     * @return Application|Factory|View
     */
    public function edit(User $user)
    {
        //using the passed in user, we can get associated data

        $roles = Role::all();
        //then go to edit screen
        return view('users.edit', compact(['user', 'roles']));


    }


    /**
     * Update the specified resource in storage.
     *
     * @param Request $request
     * @param User $user
     * @return Application|RedirectResponse|Redirector
     */
    public function update(Request $request, User $user)
    {
        //validate
        $request->validate([
            'name' => 'required',
            'password' => 'required | min:3',
        ]);
        //update the user
        $user->name = $request->name;
        $user->email = $request->email;
        $user->password = Hash::make($request->password);
        $user->save();
        $user->roles()->sync($request->role);
        //redirect
        return redirect(route('users.index'))->with('status', 'User Updated Successfully');
    }


    /**
     * Remove the specified resource from storage.
     *
     * @param User $user
     * @return Application|RedirectResponse|Redirector
     */
    public function destroy(User $user)
    {
//        DB::statement('SET FOREIGN_KEY_CHECKS = 0');
        //need to delete and make sure safe delete saves the user id
        $user->delete();
//        DB::statement('SET FOREIGN_KEY_CHECKS = 1');
        $user->deleted_by = Auth::id();
        $user->save();
        return redirect(route('users.index'))->with('status', 'User Deleted Successfully');


    }
}
