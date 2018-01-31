<?php

namespace App\Http\Controllers\API;

use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Model\AuthenticationModel;
class AuthenticationController extends Controller
{
    public function postRegister(Request $request){
        $username = $request->username;
        $password = $request->password;
        $email = $request->email;
        if(empty($username) || empty($password) || empty($email)){
            $respone = [
                "status" => false,
                "message" => "Invalid data"
            ];
        }else{
            $respone = [
                "status" => true,
                "message" => "Success Register",
                "data" => AuthenticationModel::register($username,$password,$email)
            ];
        }

        return response($respone);
    }

    public function postLogin(Request $request){
        $email = $request->email;
        $password = $request->password;

        $hashPassword = AuthenticationModel::getHashPassword($email);

        if(Hash::check($password,$hashPassword)){
            //do login
            $respone = [
                "status" => true,
                "message" => "Login Success"
            ];
        }else{
            //reponse fail
            $respone = [
                "status" => false,
                "message" => "Login Failed"
            ];

        }
        return response($respone);
    }

}
