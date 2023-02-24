<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('posts', function (Blueprint $table) {
            $table->id();
            //posts table has title, content (string), created by (user id), deleted by (user id), and deleted at (timestamp)
            $table->string('title');
            $table->string('content');
            $table->string('imageUrl')->nullable();
            $table->foreignId('created_by')->unsigned();
            $table->foreign('created_by')->references('id')->on('users');
            $table->foreignId('deleted_by')->unsigned()->nullable();
            $table->foreign('deleted_by')->references('id')->on('users');
            $table->softDeletes();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        //drop the posts table
        Schema::dropIfExists('posts');
    }
};
