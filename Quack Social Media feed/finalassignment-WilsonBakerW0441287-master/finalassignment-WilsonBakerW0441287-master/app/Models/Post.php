<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Post extends Model
{
    use HasFactory, SoftDeletes;
    //add fillable array
    protected $fillable = ['title', 'content', 'created_by'];
    function user(){
        return $this->belongsTo(User::class);
    }
}
