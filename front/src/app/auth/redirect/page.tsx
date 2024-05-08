"use client"
import axios from "axios";
import {useRouter, useSearchParams} from "next/navigation";
import {NextPage} from "next";

/**
 * User Login page
 * @page Login
 */
const RedirectPage:NextPage = () => {
    const searchParams = useSearchParams();
    const code = searchParams.get("code");
    axios.get(`http://localhost:8082/api/v1/oauth2?code=${code}`)
        .then(response => {
            // 응답을 처리합니다.
            console.log(response.data);
        })
        .catch(error => {
            // 에러를 처리합니다.
            console.error(error);
        });

    return (
        <>
        </>
    );
};
export default RedirectPage;



