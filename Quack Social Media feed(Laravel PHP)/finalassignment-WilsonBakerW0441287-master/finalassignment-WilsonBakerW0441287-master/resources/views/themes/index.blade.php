@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Themes') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        {{--add a create new admin user button and a back to posts--}}
                        <a class="btn btn-outline-success" href="{{route('themes.create')}}">
                            Create New Theme
                        </a>
                        <a class="btn btn-outline-danger" href="{{route('users.index')}}">
                            Back to Users
                        </a>


                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col" style="width: 100%">name</th>
                                <th scope="col">Created by</th>
                                <th scope="col">Last Updated by</th>
                                <th scope="col" colspan="3">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            @foreach($themes as $theme)
                                <tr>
                                    <td>{{$theme->name}}</td>
                                    <td>{{$theme->createdBy->name}}</td>
                                    <td>{{$theme->updatedBy->name ?? ""}}</td>
                                    <td>
                                        {{--this allows to edit the active country--}}
                                        <a class="btn btn-warning"
                                           href="{{route('themes.show', $theme->id)}}">Details</a>
                                    </td>
                                    <td>
                                        {{--this allows to edit the active country--}}
                                        <a class="btn btn-success"
                                           href="{{route('themes.edit', $theme->id)}}">Edit</a>
                                    </td>
                                    {{--now we need a delete call--}}
                                    <td>
                                        <form action="{{route('themes.destroy', $theme->id)}}" method="POST">
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
