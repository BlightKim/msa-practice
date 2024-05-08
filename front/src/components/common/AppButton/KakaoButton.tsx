import {styled} from "@mui/system";
import Button, {ButtonProps} from "@mui/material/Button";

interface KakaoButtonProps extends ButtonProps {
}

const KakaoButton = styled(Button)<KakaoButtonProps>(({ theme }) => ({
    background: '#fee500',
    color: '#000',
    border: '1.6px solid #fee500',
    borderRadius: '8px',
    padding: '8px 16px',
    fontSize: '16px',
    width: '324px',
    height: '48px',
    cursor: 'pointer',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    '& span': {
        width: '100%',
        textAlign: 'center',
        lineHeight: '48px',
    },
}));

function KakaoLoginButton({onClick}: {onClick?: () => void}) {
    return (
        <KakaoButton variant="contained" disableRipple onClick={onClick}>
            <span>카카오로 로그인</span>
        </KakaoButton>
    );
}

export default KakaoLoginButton;