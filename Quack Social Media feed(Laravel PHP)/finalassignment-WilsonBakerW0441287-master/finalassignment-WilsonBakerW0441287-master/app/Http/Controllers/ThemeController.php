<?php

namespace App\Http\Controllers;

use App\Models\Theme;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class ThemeController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function index()
    {
        //
        $themes = Theme::with('createdBy')->orderBy('name')->get();
        return view('themes.index', compact('themes'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
        return view('themes.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(Request $request)
    {
        //
        $request->validate([
            'name' => 'required|unique:themes',
            'cdn_url' => 'required|url|unique:themes|max:255',
        ]);

        Theme::create([
            'name' => $request['name'],
            'cdn_url' => $request['cdn_url'],
            'created_by' => Auth()->id(),
        ]);
        return redirect(route('themes.index'))->with('status', 'Theme Created Successfully');


    }

    /**
     * Display the specified resource.
     *
     * @param \App\Models\Theme $theme
     * @return \Illuminate\Http\Response
     */
    public function show(Theme $theme)
    {
        //
        return view('themes.show', compact('theme'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param \App\Models\Theme $theme
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function edit(Theme $theme)
    {
        //using the id we will have the information to edit the theme
        return view('themes.edit', compact('theme'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @param \App\Models\Theme $theme
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update(Request $request, Theme $theme)
    {
        //
        $request->validate([
            'name' => 'required|unique:themes,name,' . $theme->id,
            'cdn_url' => 'required|url|unique:themes,name,' . $theme->id . '|max:255',
        ]);

        $theme->name = $request['name'];
        $theme->cdn_url = $request['cdn_url'];
        $theme->updated_by = Auth()->id();
        $theme->save();
        return redirect(route('themes.index'))->with('status', 'Theme Updated Successfully');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param \App\Models\Theme $theme
     * @return \Illuminate\Http\Response
     */
    public function destroy(Theme $theme)
    {
        if ($theme->id = 1) {
            return redirect(route('themes.index'))->with('status', 'You cannot delete the default theme');
        }
        $theme->delete();
        $theme->deleted_by = Auth::id();
        $theme->save();
        return redirect(route('users.index'))->with('status', 'User Deleted Successfully');


    }

    public function changeTheme(Request $request)
    {
        return redirect()->back()->cookie('theme', $request->themeid, time() + (10 * 365 * 24 * 60 * 60));
    }

}
