@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Add a Admin user') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        <form action="{{route('users.store')}}" method="POST">
                            @csrf
                            <div class="form-group mb-3">
                                <label for="first_name">Full Name</label>
                                <input type="text" class="form-control" id="first_name" name="name"
                                       value="{{old('first_name')}}" placeholder="Enter full Name">

                                @error('first_name')
                                <div class="alert alert-danger">{{ $message }}</div>
                                @enderror
                            </div>
                            <div class="row mb-3">
                                <label for="email"
                                       class="">{{ __(      'Email Address') }}</label>

                                <div class="">
                                    <input id="email" type="email"
                                           class="form-control @error('email') is-invalid @enderror" name="email"
                                           value="{{ old('email') }}" required autocomplete="email">

                                    @error('email')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>


                            <div class="form-group mb-3">
                                <label for="role">Roles</label>
                                @foreach($roles as $role)
                                    <div class="form-check">
                                        <input class="form-check-input"
                                               name="role" type="checkbox"
                                               value="{{$role->id}}"
                                               id="role">
                                        <label class="form-check-label" for="role">
                                            {{$role->name}}
                                        </label>
                                    </div>
                                @endforeach
                            </div>
                            <div class="row mb-3">
                                <label for="password"
                                       class="">{{ __('Password') }}</label>

                                <div class="">
                                    <input id="password" type="password"
                                           class="form-control @error('password') is-invalid @enderror"
                                           name="password" required autocomplete="new-password">

                                    @error('password')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="row mb-0">
                                <div class="">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                    <a href="{{route('users.index')}}">
                                        <button type="button" class="btn btn-outline-danger">cancel</button>
                                    </a>
                                </div>
                            </div>


                        </form>


                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
