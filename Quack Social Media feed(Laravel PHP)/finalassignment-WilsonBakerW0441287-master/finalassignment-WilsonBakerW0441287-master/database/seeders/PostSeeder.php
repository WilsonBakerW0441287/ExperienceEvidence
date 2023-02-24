<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PostSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //add a post made by each (SEEDED) user to the database
        DB::table('posts')->insert([
            'title' => 'Serverless architecture',
            'content' => 'Test user Ripped this from PH',
            'imageUrl' => 'https://i.redd.it/rmls1erhf24a1.jpg',
            'created_by' => 1,
            'created_at' => now()
        ]);
        DB::table('posts')->insert([
            'title' => 'Jane\'s Post',
            'content' => 'This is Jane\'s post',
            'created_by' => 2,
            'created_at' => now()
        ]);
        DB::table('posts')->insert([
            'title' => 'Bob\'s Post',
            'content' => 'This is Bob\'s post',
            'created_by' => 3,
            'created_at' => now()
        ]);
        DB::table('posts')->insert([
            'title' => 'Susan\'s Post',
            'content' => 'This is Susan\'s post',
            'created_by' => 4,
            'created_at' => now()
        ]);
    }
}
