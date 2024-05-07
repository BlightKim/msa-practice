'use client';
import {Box, Checkbox, FormControlLabel, Grid, Link, Stack, TextField} from '@mui/material';
import { useRouter } from 'next/navigation';
import { useAppStore } from '@/store';
import { useEventLogout } from '@/hooks';
import { sessionStorageSet } from '@/utils';
import React from "react";
import Button from "@mui/material/Button";
import KakaoLoginButton from "@/components/common/AppButton/KakaoButton";
import {KAKAO_AUTH_URL} from "@/constants/kakaoLogin";

/**
 * Renders login form for user to authenticate
 * @component LoginForm
 */
const LoginForm = () => {
  const router = useRouter();
  const [, dispatch] = useAppStore();
  const onLogout = useEventLogout();

  const onLogin = () => {
    // TODO: AUTH: Sample of access token store, replace next line in real application
    sessionStorageSet('access_token', 'TODO:_save-real-access-token-here');

    dispatch({ type: 'LOG_IN' });
    router.replace('/'); // Redirect to home page without ability to go back
  };

  const loginKakao = () => {
    location.href=KAKAO_AUTH_URL;
  }

  return (
    <Stack alignItems="center" spacing={2} padding={2}>

      <Stack direction="row">
        <Box component="form"  noValidate sx={{ mt: 1 }}>
          <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="이메일 주소를 입력해주세요."
              name="email"
              autoFocus
          />
          <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="비밀번호를 입력해주세요"
              type="password"
              id="password"
              autoComplete="current-password"
          />
          <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="기억하기"
          />
          <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
          >
            로그인
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="#" variant="body2">
                {"계정이 없으신가요 ? 가입하세요 !"}
              </Link>
            </Grid>
          </Grid>
          <KakaoLoginButton  onClick={loginKakao}/>
        </Box>
      </Stack>
    </Stack>
  );
};

export default LoginForm;
