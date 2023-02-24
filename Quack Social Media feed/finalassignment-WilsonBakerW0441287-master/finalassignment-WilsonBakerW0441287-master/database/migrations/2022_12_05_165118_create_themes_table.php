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
        Schema::create('themes', function (Blueprint $table) {
            $table->id();
            //add name column, cdn_url, created_by, updated_by, created_at, updated_at, deleted_at, deleted_by
            $table->string('name');
            $table->string('cdn_url');
            $table->foreignId('created_by')->unsigned()->constrained('users');
            $table->foreignId('updated_by')->unsigned()->nullable()->constrained('users');
            $table->foreignId('deleted_by')->unsigned()->nullable()->constrained('users');
            //add foreign keys if contrsained is not used
//            $table->foreign('created_by')->references('id')->on('users');
//            $table->foreign('updated_by')->references('id')->on('users');
//            $table->foreign('deleted_by')->references('id')->on('users');
            //add timestamps and soft deletes
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
        Schema::dropIfExists('themes');
    }
};
