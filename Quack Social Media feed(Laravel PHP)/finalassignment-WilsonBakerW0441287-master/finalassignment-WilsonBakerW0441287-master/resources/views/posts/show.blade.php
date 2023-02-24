@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('User info') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        {{--                        add a create new admin user button--}}
                        <a href="{{route('users.index')}}">
                            <button type="button" class="btn btn-outline-danger">cancel</button>
                        </a>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Role(s)</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td>{{$user->id}}</td>
                                <td style="width: 100%">{{$user->name}}</td>
                                <td>
                                    @foreach($user->roles as $role)
                                        {{$role->name}}
                                    @endforeach
                            </tr>

                            </tbody>
                        </table>

                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
