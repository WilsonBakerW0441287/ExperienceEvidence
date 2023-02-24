<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ThemeSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        DB::table('themes')->insert([
            'name' => 'Default',
            'cdn_url' => 'https://bootswatch.com/_vendor/bootstrap/dist/css/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Cerulean',
            'cdn_url' => 'https://bootswatch.com/5/cerulean/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Darkly',
            'cdn_url' => 'https://bootswatch.com/5/darkly/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Litera',
            'cdn_url' => 'https://bootswatch.com/5/litera/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Cyborg',
            'cdn_url' => 'https://bootswatch.com/5/cyborg/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Vapor',
            'cdn_url' => 'https://bootswatch.com/5/vapor/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Quartz',
            'cdn_url' => 'https://bootswatch.com/5/quartz/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
        DB::table('themes')->insert([
            'name' => 'Sketchy',
            'cdn_url' => 'https://bootswatch.com/5/sketchy/bootstrap.min.css',
            'created_by' => 4,
            'created_at' => now()]);
    }
}
