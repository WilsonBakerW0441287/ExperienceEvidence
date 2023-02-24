@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Users') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        {{--add a create new admin user button and a back to posts AND a link to theme mgmt--}}
                        <a class="btn btn-outline-success" href="{{route('users.create')}}">
                            Create New Admin User
                        </a>

                        <a class="btn btn-outline-danger" href="{{route('posts.index')}}">
                        Back to Posts
                        </a>


                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col" colspan="2">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            @foreach($users as $user)
                                <tr>
                                    <td>{{$user->id}}</td>
                                    <td style="width: 100%">{{$user->name}}</td>
                                    <td>
                                        {{--this allows to edit the active country--}}
                                        <a class="btn btn-warning"
                                           href="{{route('users.show', $user->id)}}">Details</a>
                                    </td>
                                    <td>
                                        {{--this allows to edit the active country--}}
                                        <a class="btn btn-success"
                                           href="{{route('users.edit', $user->id)}}">Edit</a>
                                    </td>
                                    {{--now we need a delete call--}}
                                    <td>
                                        <form action="{{route('users.destroy', $user->id)}}" method="POST">
                                            @csrf
                                            @method('DELETE')
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        </table>

                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
