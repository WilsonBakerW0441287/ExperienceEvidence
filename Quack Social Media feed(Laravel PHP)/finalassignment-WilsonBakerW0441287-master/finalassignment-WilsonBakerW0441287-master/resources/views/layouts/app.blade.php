@php

    $selectedThemeid = \Illuminate\Support\Facades\Cookie::get('theme') ?? 1;//default to 1

    $selectedTheme = \App\Models\Theme::find($selectedThemeid);

@endphp

    <!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>Quack</title>

    <!-- Fonts -->
    <link rel="dns-prefetch" href="//fonts.gstatic.com">
    <link href="https://fonts.bunny.net/css?family=Nunito" rel="stylesheet">

    <!-- Scripts -->
        @vite(['resources/sass/app.scss', 'resources/js/app.js'])
    <link rel="stylesheet" href="{{$selectedTheme->cdn_url}}"/>
</head>
<body>
<div id="app">
    <nav class="navbar navbar-expand-md navbar-light bg-white shadow-sm">
        <div class="container">
            <a class="navbar-brand" href="{{ url('/') }}">
                🦆Quack Home
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="{{ __('Toggle navigation') }}">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!-- Left Side Of Navbar -->
                <ul class="navbar-nav me-auto">
                    <li>
                        <form method="POST" action="{{route("themes.change")}}">
                            @csrf
                            <select onchange="form.submit()"
                                    name="themeid" id="themeid"
                                    class="form-select"
                                    aria-label="themeid">

                                @foreach($themes as $theme)
                                    <option
                                        value="{{$theme->id}}" {{$selectedThemeid == $theme->id ? 'selected' : ''}}>{{$theme->name}}</option>
                                @endforeach
                            </select>
                        </form>
                    </li>
                </ul>


                <!-- Right Side Of Navbar -->
                <ul class="navbar-nav ms-auto">
                    <!-- Authentication Links -->
                    @guest
                        @if (Route::has('login'))
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('login') }}">{{ __('Login') }}</a>
                            </li>
                        @endif

                        @if (Route::has('register'))
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('register') }}">{{ __('Register') }}</a>
                            </li>
                        @endif
                    @else
                        <li class="nav-item dropdown">
                            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button"
                               data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                {{ Auth::user()->name }}
                            </a>

                            <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="{{ route('logout') }}"
                                   onclick="event.preventDefault();
                                                     document.getElementById('logout-form').submit();">
                                    {{ __('Logout') }}
                                </a>

                                <form id="logout-form" action="{{ route('logout') }}" method="POST" class="d-none">
                                    @csrf
                                </form>
                            </div>
                            {{-- Check if the user has the highest role and show them this button--}}
                            @if(auth()->user()->roles()->get()->pluck('name')->first() == "User Admin")
                                <a href="{{route('users.index')}}" style="text-decoration: none; color: grey">
                                    User Management
                                </a>
                            @elseif(auth()->user()->roles()->get()->pluck('name')->first() == "Theme Manager")
                                <a href="{{route('themes.index')}}" style="text-decoration: none; color: grey">
                                    Theme Management
                                </a>
                            @endif

                        </li>
                    @endguest
                </ul>
            </div>
        </div>
    </nav>

    <main class="py-4">
        @yield('content')
    </main>
</div>
</body>
</html>
